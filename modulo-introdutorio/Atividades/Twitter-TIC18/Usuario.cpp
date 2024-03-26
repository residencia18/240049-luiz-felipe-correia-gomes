#include "Social.hpp"

Usuario::Usuario() : nome_usuario(""), nome(""), seguidores(), seguindo(), tweets() {}

Usuario::Usuario(const std::string &nome_usuario, const std::string &nome)
    : nome_usuario(nome_usuario), nome(nome), seguidores(), seguindo(), tweets() {}

void Usuario::seguir(std::shared_ptr<Usuario> outroUsuario)
{
    seguindo.push_back(outroUsuario);
    outroUsuario->seguidores.push_back(std::make_shared<Usuario>(*this));

    // Mensagens de depuração
    std::cout << this->getNomeUsuario() << " está seguindo " << outroUsuario->getNomeUsuario() << std::endl;
    std::cout << outroUsuario->getNomeUsuario() << " tem um novo seguidor: " << this->getNomeUsuario() << std::endl;
}

void Usuario::postarTweet(const std::string &conteudo)
{
    std::shared_ptr<Tweet> tweet = std::make_shared<Tweet>(std::make_shared<Usuario>(*this), conteudo);
    this->tweets.push_back(tweet);
}

std::vector<std::shared_ptr<Tweet>> Usuario::receberFeed()
{
    std::vector<std::shared_ptr<Tweet>> feed;
    for (std::shared_ptr<Usuario> seguido : seguindo)
    {
        for (std::shared_ptr<Tweet> tweet : seguido->tweets)
        {
            feed.push_back(tweet);
        }
    }
    return feed;
}

// Adicionar tweet ao vector
void Usuario::adicionarTweet(std::shared_ptr<Tweet> tweet)
{
    tweets.push_back(tweet);
}

void Usuario::adicionarSeguidor(std::shared_ptr<Usuario> usuario)
{
    seguidores.push_back(usuario);
}

void Usuario::adicionarSeguindo(std::shared_ptr<Usuario> usuario)
{
    seguindo.push_back(usuario);
}

// Getters
std::string Usuario::getNomeUsuario() { return nome_usuario; }
std::string Usuario::getNome() { return nome; }
std::vector<std::shared_ptr<Usuario>> Usuario::getSeguidores() { return seguidores; }
std::vector<std::shared_ptr<Usuario>> Usuario::getSeguindo() { return seguindo; }
std::vector<std::shared_ptr<Tweet>> Usuario::getTweets() { return tweets; }

// Método de serialização para usuários
std::string Usuario::serializar() const
{
    std::stringstream ss;
    ss << nome_usuario << ",";
    ss << nome << ",";

    for (std::shared_ptr<Usuario> seguidor : seguidores)
    {
        ss << seguidor->getNomeUsuario() << ",";
    } 

    ss << "\n";

    return ss.str();
}

// Método de desserialização para usuário
std::shared_ptr<Usuario> Usuario::desserializar(RedeSocial *redeSocial, const std::string &dados)
{
    std::stringstream ss(dados);
    std::string nome_usuario, nome;
    std::getline(ss, nome_usuario, ',');
    std::getline(ss, nome, ',');

    std::shared_ptr<Usuario> usuario = std::make_shared<Usuario>(nome_usuario, nome);

    std::string seguidor;
    while (std::getline(ss, seguidor, ','))
    {
        (redeSocial->buscarUsuario(seguidor))->seguir(usuario);
    }


    return usuario;
}

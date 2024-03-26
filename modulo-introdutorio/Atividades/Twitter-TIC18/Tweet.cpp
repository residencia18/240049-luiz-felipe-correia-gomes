#include "Social.hpp"

// Construtores
Tweet::Tweet() : autor(nullptr), conteudo(""), data_criacao() {}

Tweet::Tweet(std::shared_ptr<Usuario> autor, const std::string &conteudo)
    : autor(autor), conteudo(conteudo)
{
    // Registra a data e hora de criação do tweet
    std::time_t now = std::time(nullptr);
    data_criacao = *std::localtime(&now);
}

// Getters
std::shared_ptr<Usuario> Tweet::getAutor() const { return autor; }
std::string Tweet::getConteudo() const { return conteudo; }
std::tm Tweet::getDataCriacao() const { return data_criacao; }
std::string Tweet::getDataCriacaoString() const
{
    std::stringstream ss;
    ss << std::put_time(&data_criacao, "%Y-%m-%d %H:%M:%S");
    return ss.str();
}

void Tweet::setAutor(std::shared_ptr<Usuario> autor)
{
    this->autor = autor;
}

void Tweet::setConteudo(const std::string &conteudo)
{
    this->conteudo = conteudo;
}

void Tweet::setDataCriacao(const std::tm &dataCriacao)
{
    data_criacao = dataCriacao;
}

// Método de serialização para tweets
std::string Tweet::serializar() const
{
    std::stringstream ss;
    ss << getAutor()->getNomeUsuario() << ",";
    ss << std::put_time(&data_criacao, "%Y-%m-%d %H:%M:%S") << ","; // Formata a data e hora
    ss << conteudo << "\n";                                         // Use um caractere de nova linha para separar os tweets
    return ss.str();
}

// Método de desserialização para tweets
std::shared_ptr<Tweet> Tweet::desserializar(RedeSocial *redeSocial, const std::string &dados)
{
    std::stringstream ss(dados);
    std::string nomeUsuario, dataCriacaoStr, conteudo;

    std::getline(ss, nomeUsuario, ',');
    std::getline(ss, dataCriacaoStr, ',');
    std::getline(ss, conteudo, '\n'); // Caractere de nova linha para separar os tweets

    std::tm dataCriacao = {}; // Inicializa a estrutura de tempo
    std::istringstream dataStream(dataCriacaoStr);
    dataStream >> std::get_time(&dataCriacao, "%Y-%m-%d %H:%M:%S"); // Realiza a análise da data e hora

    // Encontrar usuário
    std::shared_ptr<Usuario> autor = std::make_shared<Usuario>();
    for (std::shared_ptr<Usuario> usuario : redeSocial->getUsuarios())
    {
        if (usuario->getNomeUsuario() == nomeUsuario)
        {
            autor = usuario;
            break;
        }
    }

    // Construir o objeto de tweet com os dados desserializados
    std::shared_ptr<Tweet> tweet = std::make_shared<Tweet>(autor, conteudo);
    tweet->setDataCriacao(dataCriacao);

    return tweet;
}

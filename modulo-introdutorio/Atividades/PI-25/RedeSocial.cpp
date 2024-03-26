#include "Social.hpp"

std::vector<std::shared_ptr<Usuario>> RedeSocial::getUsuarios()
{
    return usuarios;
}

void RedeSocial::adicionarUsuario(std::shared_ptr<Usuario> usuario)
{
    usuarios.push_back(usuario);
}

void RedeSocial::registrarUsuario(const std::string &nome_usuario, const std::string &nome)
{

    std::shared_ptr<Usuario> novoUsuario = std::make_shared<Usuario>(nome_usuario, nome);
    usuarios.push_back(novoUsuario);
}

std::shared_ptr<Usuario> RedeSocial::buscarUsuario(const std::string &nome_usuario)
{
    for (std::shared_ptr<Usuario> usuario : usuarios)
    {
        if (usuario->getNomeUsuario() == nome_usuario)
        {
            return usuario;
        }
    }
    return nullptr; // ponteiro nulo
}

std::vector<std::shared_ptr<Usuario>> RedeSocial::listarUsuarios()
{
    return usuarios;
}

std::vector<std::shared_ptr<Tweet>> RedeSocial::listarTweets()
{
    std::vector<std::shared_ptr<Tweet>> allTweets;
    for (std::shared_ptr<Usuario> usuario : usuarios)
    {
        for (std::shared_ptr<Tweet> tweet : usuario->getTweets())
        {
            allTweets.push_back(tweet);
        }
    }
    return allTweets;
}

// Função para salvar dados de tweets em um arquivo
void RedeSocial::salvarTweets(const std::string &nomeArquivo)
{
    std::ofstream arquivo(nomeArquivo);
    if (arquivo.is_open())
    {
        for (std::shared_ptr<Usuario> usuario : usuarios)
        {
            for (std::shared_ptr<Tweet> tweet : usuario->getTweets())
            {
                arquivo << tweet->serializar();
            }
        }
        arquivo.close();
        std::cout << "Dados de tweets salvos em " << nomeArquivo << std::endl;
    }
}

// Função para carregar dados de tweets de um arquivo
void RedeSocial::carregarTweets(const std::string &nomeArquivo)
{
    std::vector<std::shared_ptr<Tweet>> tweets;

    std::ifstream arquivo(nomeArquivo);
    if (arquivo.is_open())
    {
        std::string linha;
        while (std::getline(arquivo, linha))
        {
            std::shared_ptr<Tweet> tweet = Tweet::desserializar(this, linha);

            // Adicionar tweet ao Usuario
            std::string nomeUsuarioAutor = tweet->getAutor()->getNomeUsuario();
            std::shared_ptr<Usuario> autor = buscarUsuario(nomeUsuarioAutor);
            autor->adicionarTweet(tweet);

            // Mensagem de depuração para verificar o conteúdo do tweet carregado
            std::cout << "Tweet carregado: " << tweet->getConteudo() << std::endl;

            // Armazenar os tweets carregados temporariamente em um vetor
            tweets.push_back(tweet);
        }
        arquivo.close();

        std::cout << "Dados de tweets carregados de " << nomeArquivo << std::endl;
    }
    else
    {
        std::cerr << "Erro ao abrir o arquivo " << nomeArquivo << " para carregar dados de tweets." << std::endl;
    }
}

// Função para salvar dados de usuários em um arquivo
void RedeSocial::salvarUsuarios(const std::string &nomeArquivo)
{
    std::ofstream arquivo(nomeArquivo);
    if (arquivo.is_open())
    {
        for (std::shared_ptr<Usuario> usuario : usuarios)
        {
            arquivo << usuario->serializar();
        }
        arquivo.close();
        std::cout << "Dados de usuários salvos em " << nomeArquivo << std::endl;
    }
    else
    {
        std::cerr << "Erro ao abrir o arquivo " << nomeArquivo << " para salvar dados de usuários." << std::endl;
    }
}

// Função para carregar dados de usuários de um arquivo
void RedeSocial::carregarUsuarios(const std::string &nomeArquivo)
{
    std::ifstream arquivo(nomeArquivo);
    if (arquivo.is_open())
    {
        std::string linha;
        while (std::getline(arquivo, linha))
        {
            std::shared_ptr<Usuario> usuario = Usuario::desserializar(this, linha);
            usuarios.push_back(usuario);
        }
        arquivo.close();

        std::cout << "Dados de usuários carregados de " << nomeArquivo << std::endl;
    }
    else
    {
        std::cerr << "Erro ao abrir o arquivo " << nomeArquivo << " para carregar dados de usuários." << std::endl;
    }
}

// Mostrar todos os usuários da rede social
void RedeSocial::mostrarUsuarios()
{
    for (std::shared_ptr<Usuario> usuario : usuarios)
    {
        std::cout << usuario->getNome() << " - " << usuario->getNomeUsuario() << std::endl;
    }
}

// Mostrar usuário específico
void RedeSocial::mostrarUsuario(std::shared_ptr<Usuario> usuario)
{
    std::cout << usuario->getNomeUsuario() << std::endl;

    std::cout << "Seguindo: ";
    for (std::shared_ptr<Usuario> seguido : usuario->getSeguindo())
    {
        std::cout << seguido->getNomeUsuario() << " ";
    }

    std::cout << "Seguidores: ";
    for (std::shared_ptr<Usuario> seguidor : usuario->getSeguidores())
    {
        std::cout << seguidor->getNomeUsuario() << " ";
    }
}
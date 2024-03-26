#ifndef REDESOCIAL_HPP
#define REDESOCIAL_HPP

#include <iostream>
#include <string>
#include <vector>
#include <ctime>
#include <sstream>
#include <iomanip>
#include <fstream>
#include <algorithm>
#include <memory>

class Tweet;
class Usuario;
class RedeSocial;

class Usuario
{
public:
    Usuario();
    Usuario(const std::string &nome_usuario, const std::string &nome);
    void seguir(std::shared_ptr<Usuario> outroUsuario);
    void postarTweet(const std::string &conteudo);
    void adicionarTweet(std::shared_ptr<Tweet> tweet);
    void adicionarSeguidor(std::shared_ptr<Usuario> usuario);
    void adicionarSeguindo(std::shared_ptr<Usuario> usuario);
    std::vector<std::shared_ptr<Tweet>> receberFeed();
    std::string getNomeUsuario();
    std::string getNome();
    std::vector<std::shared_ptr<Usuario>> getSeguidores();
    std::vector<std::shared_ptr<Usuario>> getSeguindo();
    std::vector<std::shared_ptr<Tweet>> getTweets();
    std::string serializar() const;
    static std::shared_ptr<Usuario> desserializar(RedeSocial *redeSocial, const std::string &dados);

private:
    std::string nome_usuario;
    std::string nome;
    std::vector<std::shared_ptr<Usuario>> seguidores;
    std::vector<std::shared_ptr<Usuario>> seguindo;
    std::vector<std::shared_ptr<Tweet>> tweets;
};

class Tweet
{
public:
    Tweet();
    Tweet(std::shared_ptr<Usuario> autor, const std::string &conteudo);
    std::shared_ptr<Usuario> getAutor() const;
    std::string getConteudo() const;
    std::tm getDataCriacao() const;
    std::string getDataCriacaoString() const;
    void setAutor(std::shared_ptr<Usuario> autor);
    void setConteudo(const std::string &conteudo);
    void setDataCriacao(const std::tm &dataCriacao);
    std::string serializar() const;
    static std::shared_ptr<Tweet> desserializar(RedeSocial *redeSocial, const std::string &dados);

private:
    std::shared_ptr<Usuario> autor;
    std::string conteudo;
    std::tm data_criacao;
};

class RedeSocial
{
public:
    void adicionarUsuario(std::shared_ptr<Usuario> usuario);
    void registrarUsuario(const std::string &nome_usuario, const std::string &nome);
    std::shared_ptr<Usuario> buscarUsuario(const std::string &nome_usuario);
    std::vector<std::shared_ptr<Usuario>> listarUsuarios();
    std::vector<std::shared_ptr<Tweet>> listarTweets();
    std::vector<std::shared_ptr<Usuario>> getUsuarios();
    void salvarTweets(const std::string &nomeArquivo);
    void carregarTweets(const std::string &nomeArquivo);
    void salvarUsuarios(const std::string &nomeArquivo);
    void carregarUsuarios(const std::string &nomeArquivo);
    void mostrarUsuarios();
    void mostrarUsuario(std::shared_ptr<Usuario> usuario);

private:
    std::vector<std::shared_ptr<Usuario>> usuarios;
};

#endif // REDESOCIAL_HPP

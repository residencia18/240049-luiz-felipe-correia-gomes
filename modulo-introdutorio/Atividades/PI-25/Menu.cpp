#include "Social.hpp"

const std::string SAVE_USERS = "users";
const std::string SAVE_TWEETS = "tweets";

int main()
{
    // Instancia o objeto RedeSocial
    RedeSocial redeSocial;

    // Carregando usuários e tweets
    redeSocial.carregarUsuarios(SAVE_USERS);
    redeSocial.carregarTweets(SAVE_TWEETS);

    int opcao;

    redeSocial.mostrarUsuarios();

    do
    {
        std::cout << std::endl
                  << std::endl;
        std::cout << "==== Menu Principal ====" << std::endl;
        std::cout << "1. Registrar Usuário" << std::endl;
        std::cout << "2. Postar Tweet" << std::endl;
        std::cout << "3. Seguir Usuário" << std::endl;
        std::cout << "4. Ver Feed" << std::endl;
        std::cout << "0. Sair" << std::endl;
        std::cout << "Escolha uma opção: ";
        std::cin >> opcao;

        switch (opcao)
        {
        case 1:
        {
            std::string nome_usuario, nome;
            std::cout << "Digite o nome de usuário: ";
            std::cin >> nome_usuario;
            std::cout << "Digite o nome real: ";
            std::cin.ignore();
            std::getline(std::cin, nome);
            redeSocial.registrarUsuario(nome_usuario, nome);
            std::cout << "Usuário registrado com sucesso." << std::endl;
            break;
        }
        case 2:
        {
            std::string nome_usuario, conteudo;
            std::cout << "Digite seu nome de usuário: ";
            std::cin >> nome_usuario;
            std::shared_ptr<Usuario> usuario = redeSocial.buscarUsuario(nome_usuario);
            if (usuario)
            {
                redeSocial.mostrarUsuario(usuario);
                std::cout << std::endl;

                std::cout << "Digite o conteúdo do tweet: ";
                std::cin.ignore();
                std::getline(std::cin, conteudo);
                usuario->postarTweet(conteudo);
                std::cout << "Tweet postado com sucesso." << std::endl;
            }
            else
            {
                std::cout << "Usuário não encontrado." << std::endl;
            }
            break;
        }
        case 3:
        {
            std::string nome_usuario, seguir_usuario;
            std::cout << "Digite seu nome de usuário: ";
            std::cin >> nome_usuario;
            std::shared_ptr<Usuario> usuario = redeSocial.buscarUsuario(nome_usuario);
            if (usuario)
            {
                std::cout << "Digite o nome de usuário que deseja seguir: ";
                std::cin >> seguir_usuario;
                std::shared_ptr<Usuario> seguido = redeSocial.buscarUsuario(seguir_usuario);
                if (seguido)
                {
                    usuario->seguir(seguido);
                    std::cout << "Agora você está seguindo " << seguir_usuario << "." << std::endl;
                }
                else
                {
                    std::cout << "Usuário a ser seguido não encontrado." << std::endl;
                }
            }
            else
            {
                std::cout << "Usuário não encontrado." << std::endl;
            }
            break;
        }
        case 4:
        {
            std::string nome_usuario;
            std::cout << "Digite seu nome de usuário: ";
            std::cin >> nome_usuario;
            std::shared_ptr<Usuario> usuario = redeSocial.buscarUsuario(nome_usuario);

            if (usuario)
            {
                std::vector<std::shared_ptr<Tweet>> feed = usuario->receberFeed();

                /// Função lambda para ordenar os tweets cronologicamente
                std::sort(feed.begin(), feed.end(), [](std::shared_ptr<Tweet> a, std::shared_ptr<Tweet>b)
                          {
                    // Compare as datas e horas dos tweets
                    if (a->getDataCriacao().tm_year != b->getDataCriacao().tm_year) {
                    return a->getDataCriacao().tm_year > b->getDataCriacao().tm_year;
                    }
                    if (a->getDataCriacao().tm_mon != b->getDataCriacao().tm_mon) {
                    return a->getDataCriacao().tm_mon > b->getDataCriacao().tm_mon;
                    }
                    if (a->getDataCriacao().tm_mday != b->getDataCriacao().tm_mday) {
                    return a->getDataCriacao().tm_mday > b->getDataCriacao().tm_mday;
                    }
                    if (a->getDataCriacao().tm_hour != b->getDataCriacao().tm_hour) {
                    return a->getDataCriacao().tm_hour > b->getDataCriacao().tm_hour;
                    }
                    if (a->getDataCriacao().tm_min != b->getDataCriacao().tm_min) {
                    return a->getDataCriacao().tm_min > b->getDataCriacao().tm_min;
                    }
                    return a->getDataCriacao().tm_sec > b->getDataCriacao().tm_sec; });

                std::cout << "=== Feed de " << nome_usuario << " ===" << std::endl;
                if (feed.empty())
                {
                    std::cout << "O feed está vazio." << std::endl; // Mensagem de depuração
                }
                else
                {
                    for (std::shared_ptr<Tweet> tweet : feed)
                    {
                        std::cout << tweet->getAutor()->getNomeUsuario() << " - " << tweet->getDataCriacaoString() << std::endl;
                        std::cout << tweet->getConteudo() << std::endl;
                        std::cout << "----------------------" << std::endl;
                    }
                }
            }
            else
            {
                std::cout << "Usuário não encontrado." << std::endl;
            }
            break;
        }
        case 0:
            // Salvando e saindo do programa
            redeSocial.salvarUsuarios(SAVE_USERS);
            redeSocial.salvarTweets(SAVE_TWEETS);
            std::cout << "Saindo..." << std::endl;
            break;
        default:
            std::cout << "Opção inválida. Tente novamente." << std::endl;
        }
    } while (opcao != 0);

    return 0;
}
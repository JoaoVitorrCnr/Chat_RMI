package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import server.ChatServer;

public class StartClient {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Digite seu nome: ");
            String name = scanner.nextLine();

            System.setProperty("java.rmi.server.disableHttp", "false");
            
            String serverAddress = "192.168.1.230";
            Registry registry = LocateRegistry.getRegistry(serverAddress, 1099);
            ChatServer server = (ChatServer) registry.lookup("ChatServer");
            
            // Registra cliente
            ChatClient client = new ChatClientImpl(name);
            server.registerClient(client);
            
            System.out.println("Conectado ao chat! Digite suas mensagens ('sair' para encerrar):");
            
            // Loop de mensagens
            while (true) {
                String message = scanner.nextLine();
                if ("sair".equalsIgnoreCase(message)) {
                    server.disconnectClient(client);
                    break;
                }
                server.broadcastMessage(name + ": " + message);
            }
            
            System.out.println("Conexão encerrada.");
            scanner.close();
            System.exit(0);
        } catch (Exception e) {
            System.err.println("Erro no cliente: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
package server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class StartServer {
    public static void main(String[] args) {
        try {
            // Configura o uso de HTTP tunneling (útil para firewalls)
            System.setProperty("java.rmi.server.disableHttp", "false");
            String ip = "192.168.1.230"; 
            System.setProperty("java.rmi.server.hostname", ip);
            
            // Cria e registra o servidor
            ChatServer server = new ChatServerImpl();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("ChatServer", server);
            
            System.out.println("Servidor de chat RMI pronto na porta 1099...");
            System.out.println("Endereço do servidor: rmi://" + ip + ":1099/ChatServer");
        } catch (Exception e) {
            System.err.println("Erro no servidor: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
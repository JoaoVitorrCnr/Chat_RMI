package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import client.ChatClient;

public class ChatServerImpl extends UnicastRemoteObject implements ChatServer {
    private List<ChatClient> clients = new ArrayList<>();

    public ChatServerImpl() throws RemoteException {
        super();
    }

    @Override
    public synchronized void registerClient(ChatClient client) throws RemoteException {
        clients.add(client);
        broadcastMessage("Sistema: Novo usuário conectado!");
    }

    @Override
    public synchronized void broadcastMessage(String message) throws RemoteException {
        System.out.println("Mensagem recebida: " + message);
        for (ChatClient client : new ArrayList<>(clients)) {
            try {
                client.receiveMessage(message);
            } catch (RemoteException e) {
                clients.remove(client);
                System.out.println("Cliente desconectado: " + e.getMessage());
            }
        }
    }

    @Override
    public synchronized void disconnectClient(ChatClient client) throws RemoteException {
        clients.remove(client);
        broadcastMessage("Sistema: Um usuário saiu do chat.");
    }
}
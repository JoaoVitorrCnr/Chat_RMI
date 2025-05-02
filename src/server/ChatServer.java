package server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import client.ChatClient;

public interface ChatServer extends Remote {
    void registerClient(ChatClient client) throws RemoteException;
    void broadcastMessage(String message) throws RemoteException;
    void disconnectClient(ChatClient client) throws RemoteException;
}
package client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ChatClientImpl extends UnicastRemoteObject implements ChatClient {
    private String name;

    public ChatClientImpl(String name) throws RemoteException {
        super();
        this.name = name;
    }

    @Override
    public void receiveMessage(String message) throws RemoteException {
        System.out.println(message);
    }

    public String getName() {
        return name;
    }
}
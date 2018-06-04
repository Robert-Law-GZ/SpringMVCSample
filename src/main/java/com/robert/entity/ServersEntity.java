package com.robert.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "servers", schema = "mysql", catalog = "")
public class ServersEntity {
    private String serverName;
    private String host;
    private String db;
    private String username;
    private String password;
    private int port;
    private String socket;
    private String wrapper;
    private String owner;

    @Id
    @Column(name = "Server_name")
    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    @Basic
    @Column(name = "Host")
    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    @Basic
    @Column(name = "Db")
    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    @Basic
    @Column(name = "Username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "Password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "Port")
    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Basic
    @Column(name = "Socket")
    public String getSocket() {
        return socket;
    }

    public void setSocket(String socket) {
        this.socket = socket;
    }

    @Basic
    @Column(name = "Wrapper")
    public String getWrapper() {
        return wrapper;
    }

    public void setWrapper(String wrapper) {
        this.wrapper = wrapper;
    }

    @Basic
    @Column(name = "Owner")
    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServersEntity that = (ServersEntity) o;
        return port == that.port &&
                Objects.equals(serverName, that.serverName) &&
                Objects.equals(host, that.host) &&
                Objects.equals(db, that.db) &&
                Objects.equals(username, that.username) &&
                Objects.equals(password, that.password) &&
                Objects.equals(socket, that.socket) &&
                Objects.equals(wrapper, that.wrapper) &&
                Objects.equals(owner, that.owner);
    }

    @Override
    public int hashCode() {

        return Objects.hash(serverName, host, db, username, password, port, socket, wrapper, owner);
    }
}

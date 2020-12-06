package com.example.ajare_v2;

public class Service {
    private String id;
    private String serviceName;
    private String documents;
    public Service(){
    }
    public Service(String id, String serviceName, String documents) {
        this.id = id;
        this.serviceName = serviceName;
        this.documents = documents;
    }
    public Service(String serviceName, String documents) {
        this.serviceName = serviceName;
        this.documents = documents;
    }

    public void setId(String id)  {this.id = id;}
    public String getId() {
        return id;
    }
    public void setServiceName(String serviceName) {
        this.serviceName=serviceName;
    }
    public String getServiceName() {
        return serviceName;
    }
    public void setDocuments(String documents) {
        this.documents = documents;
    }
    public String getDocuments() {
        return documents;
    }
}


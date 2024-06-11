package models;

import javax.xml.ws.Endpoint;


public class PublishServices {

	public static void main(String[] args) {
        Endpoint.publish("http://localhost:8080/soap/soapi", new SOAPIImpl());
    }

}

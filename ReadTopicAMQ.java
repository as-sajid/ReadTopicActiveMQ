/* Add the jar files from activeMQ server*/
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.io.InputStream;
import java.jms.Connection;
import java.jms.ConnectionFactory;
import java.jms.Session;
import java.jms.Textmessage;
import java.jms.Topic;
import java.jms.Message;
import java.jms.MessageConsumer;
import java.jms.JMSException;
import org,apache.activemq.ActiveMQConnectionFactory;
public class ReadTopicAMQ {
   

    public static void main(String[] args) throws Exception {
        int time_to_run=Integer.parseint(args0])*60000;
        Stringvirtual_topic_name=args[1];
        Properties prop = new Properties();InputStream input=null;
        String myurl=null;
        try{
            input =new FileInputStream("Path to properties file");
            prop.load(input);
            myurl=prop.getProperty("URL");
            System.out.println("The target server address is :" + myurl +"\n");
        } catch(IOException ex)
        {System.out.println("Could not load the properties file");
         } //end of try catch
    ConnectionFactory connectionfactory =new ActiveMQConnectionfactory(myurl);
    Connection connection =connectionfactory.createConnection();
    Session session =connection.createSession(false,session.Auto_ACKNOWLEDGE);
    Topic queue = session.createTopic(virtual_topic_name);
    MessageConsumer consumer=session.createConsumer(queue);
    connection.start();
    int message_count=0;
    while(true){

        message msg=consumer.receive(time_to_run);
        if (msg instanceof TextMessage){
            ++message_count;
            TextMessage tm=(TextMessage) msg;
            System.out.println("Message number :"+message_count +":");
            System.out.println(tm.getText());
            System.out.println("The message properties are :");
            System.out.ptintln("Property1"+tm.getStringProperty("property1"));
            System.out.ptintln("Property2"+tm.getStringProperty("Propertty2"));
            msg.cknowledge();
        } else {
            System.ou.println("\n\nQueue Empty / No more message to recieve !.");
            connection.stop();
            break;
        } //end else if else
     

        }//end of while
        System.out.println("The total number of messages recieved is" + message_count +".");
        connection.close();
        System.out.println("Connection closed");
    }//end of main
}
 //end of class
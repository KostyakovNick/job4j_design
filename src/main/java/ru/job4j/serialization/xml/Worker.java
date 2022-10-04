package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "worker")
@XmlAccessorType(XmlAccessType.FIELD)
public class Worker {

    @XmlAttribute
    private boolean q;
    private int experience;
    private String post;
    private Person person;

    @XmlElementWrapper(name = "sizes")
    @XmlElement(name = "size")
    private int[] size;

    public Worker() { }

    public Worker(boolean q, int experience, String post, Person person, int... size) {
        this.q = q;
        this.experience = experience;
        this.post = post;
        this.person = person;
        this.size = size;
    }

    @Override
    public String toString() {
        return "Worker{"
                + "q=" + q
                + ", experience=" + experience
                + ", post=" + post
                + ", person=" + person
                + ", size=" + Arrays.toString(size)
                + '}';
    }

    public static void main(String[] args) throws JAXBException {

        final Worker worker = new Worker(true, 6, "Engineer",
            new Person(false, 40, new Contact("+79130524928"), "Worker", "Free"), 52, 165, 42, 58);

        JAXBContext context = JAXBContext.newInstance(Worker.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(worker, writer);
            String result = writer.getBuffer().toString();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package com.robert.controller.activemq;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.TextMessage;
import javax.management.MBeanServerConnection;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import com.robert.activemq.ConsumerService;
import com.robert.activemq.ProducerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/amq")
public class ActivemqDemo {

    @Resource(name = "demoQueueDestination")
    private Destination demoQueueDestination;

    @Resource(name = "producerService")
    private ProducerService producer;

    @Resource(name = "consumerService")
    private ConsumerService consumer;

    @RequestMapping(value = "/producer", method = RequestMethod.GET)
    public ModelAndView producer() {
        System.out.println("--------------go producer");

        Date now = new Date();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String time = sdf.format(now);

        System.out.println(time);

        ModelAndView mv = new ModelAndView();

        mv.addObject("time", time);
        mv.setViewName("jms_producer");
        return mv;
    }

    @RequestMapping(value = "/onsend", method = RequestMethod.POST)
    public ModelAndView producer(@RequestParam("message") String message) {
        System.out.println("---------send to jms");
        ModelAndView mv = new ModelAndView();
        producer.sendMessage(demoQueueDestination, message);
        mv.setViewName("activemq");
        return mv;
    }

    @RequestMapping(value = "/receive", method = RequestMethod.GET)
    public ModelAndView queue_receive() throws JMSException {
        System.out.println("-----------receive message");
        ModelAndView mv = new ModelAndView();

        TextMessage tm = consumer.receive(demoQueueDestination);

        if (tm == null) {
            mv.addObject("textMessage", "Queue is Empty");
        } else {
            mv.addObject("textMessage", tm.getText());
        }
        mv.setViewName("jms_receiver");
        return mv;
    }

    public ModelAndView jmsManager() throws IOException {
        System.out.println("--------------jms manager");
        ModelAndView mv = new ModelAndView();

        mv.setViewName("activemq");

        JMXServiceURL url = new JMXServiceURL("");

        JMXConnector connector = JMXConnectorFactory.connect(url);
        connector.connect();
        MBeanServerConnection connection = connector.getMBeanServerConnection();

        return mv;
    }

}
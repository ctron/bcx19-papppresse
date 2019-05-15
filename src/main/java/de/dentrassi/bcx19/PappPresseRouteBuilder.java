package de.dentrassi.bcx19;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@Component
public class PappPresseRouteBuilder extends RouteBuilder {

    public void configure() {

        from("seda:value")
            .to("stream:out")
            .to("amqp:papppresse")
            ;

        from("milo-client:tcp://192.168.2.5:4840?node=RAW(ns=2;s=Application.GLV_AxisInterface.rCmdPosition_gb)&allowedSecurityPolicies=None")
            .transform()
            .body().setBody().simple("body.value.value", String.class)
            .setBody(ex -> {
                final Map<String,String> map = new HashMap<>();
                map.put("value", ex.getIn().getBody(String.class));
                return map;
            })
            .marshal().json(JsonLibrary.Gson)
            .to("seda:value")
            ;
        
        /*
        from("amqp:papppresse")
            .to("stream:out")
            ;
            */
    }

}
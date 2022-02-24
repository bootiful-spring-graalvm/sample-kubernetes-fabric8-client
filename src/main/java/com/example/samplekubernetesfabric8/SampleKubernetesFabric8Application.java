package com.example.samplekubernetesfabric8;

import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SampleKubernetesFabric8Application {

    @Bean
    ApplicationRunner runner(KubernetesClient client) {
        return args -> client
                .namespaces()
                .list()
                .getItems()
                .stream()
                .map( ns -> ns.getMetadata().getName())
                .forEach(System.out::println);
    }

    @Bean//(destroyMethod = "close")
    KubernetesClient kubernetesClient() {
        return new DefaultKubernetesClient();
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SampleKubernetesFabric8Application.class, args);
    }

}

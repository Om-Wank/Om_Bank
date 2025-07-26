package com.om.the_java_om_bank;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info=@Info(
				title = "The Om Bank App",
			description = "Backend Rest APIs for Om Bank",
			version = "v1.0",
			contact = @Contact(
					name = "Om Wankhede",
					email = "omwankhede.nbnssoe.it@gmail.com",
					url="https://github.com/Om-Wank/Om_Bank"
			),
			license = @License(
					name = "Om wankhede",
					url="https://github.com/Om-Wank/Om_Bank"
			)
	    ),
	externalDocs=@ExternalDocumentation(
			description = "The Om Bank App Documentation",
			url = "https://github.com/Om-Wank/Om_Bank"
	)
)
public class TheJavaOmBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(TheJavaOmBankApplication.class, args);
	}

}

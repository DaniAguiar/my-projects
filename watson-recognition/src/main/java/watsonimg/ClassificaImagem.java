package watsonimg;

import java.io.InputStream;
import java.util.Arrays;

import com.ibm.cloud.sdk.core.http.HttpConfigOptions;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.cloud.sdk.core.service.exception.NotFoundException;
import com.ibm.cloud.sdk.core.service.exception.RequestTooLargeException;
import com.ibm.cloud.sdk.core.service.exception.ServiceResponseException;
import com.ibm.watson.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.visual_recognition.v3.model.ClassifiedImages;
import com.ibm.watson.visual_recognition.v3.model.ClassifyOptions;

public class ClassificaImagem {
    public static void main(String[] args) {
        String serviceUrl = "https://api.us-south.visual-recognition.watson.cloud.ibm.com/instances/a2bc5dc6-2411-4a55-8fef-3722e6fea8c1";
        String apiKey = "U7gX_lJ90TEoaADZuj-fo0IkdKRs5ByCz1WeH-04m4PN";
        String apiVersionDate = "2018-03-19"; // Versão da API
        String fileVeiculos = "veiculos.jpg";
        String filePessoas = "pessoas.jpg";
        String fileFrutas = "fruitbowl.jpg";

        IamAuthenticator authenticator = new IamAuthenticator(apiKey);
        VisualRecognition visualRecognition = new VisualRecognition(apiVersionDate, authenticator);
        visualRecognition.setServiceUrl(serviceUrl);

        HttpConfigOptions configOptions = new HttpConfigOptions
                .Builder()
                .disableSslVerification(true)
                .build();
        visualRecognition.configureClient(configOptions);

        classifyImage(fileVeiculos, visualRecognition);
        classifyImage(filePessoas, visualRecognition);
        classifyImage(fileFrutas, visualRecognition);
    }

    private static void classifyImage(final String image, final VisualRecognition visualRecognition) {
        try {
            InputStream imagesStream = abrirArquivo(image);
            ClassifyOptions classifyOptions = new ClassifyOptions.Builder()
                    .imagesFile(imagesStream)
                    .imagesFilename(image)
                    .classifierIds(Arrays.asList("default")) // default ou food
                    .build();
            ClassifiedImages result = visualRecognition.classify(classifyOptions).execute().getResult();
            System.out.println(result);

        } catch (NotFoundException e) {
            // Handle Not Found (404) exception
        } catch (RequestTooLargeException e) {
            // Handle Request Too Large (413) exception
        } catch (ServiceResponseException e) {
            // Base class for all exceptions caused by error responses from the service
            System.out.println("Service returned status code " + e.getStatusCode() + ": " + e.getMessage());
        }
    }

    private static InputStream abrirArquivo(String fileName) {
        return ClassificaImagem.class.getClassLoader().getResourceAsStream(fileName);
    }

}

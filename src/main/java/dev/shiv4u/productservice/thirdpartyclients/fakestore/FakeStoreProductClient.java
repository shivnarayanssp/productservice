package dev.shiv4u.productservice.thirdpartyclients.fakestore;

import dev.shiv4u.productservice.dtos.GenericProductDto;
import dev.shiv4u.productservice.exceptions.NotFoundException;
import dev.shiv4u.productservice.thirdpartyclients.fakestore.dtos.FakeStoreProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Component
public class FakeStoreProductClient {
    private final String productPath = "/products/";
    @Value("${fakestore.api.baseurl}")
    private String fakeStoreApiBaseUrl;
    @Value("${fakestore.api.product}")
    private String fakeStoreProductPath;
    private String productUrl = fakeStoreApiBaseUrl + productPath ;
    private String productRequestUrl = fakeStoreApiBaseUrl + fakeStoreProductPath;
    private final RestTemplateBuilder restTemplateBuilder;

    @Autowired
    public FakeStoreProductClient(RestTemplateBuilder restTemplateBuilder,
                                  @Value("${fakestore.api.baseurl}") String fakeStoreApiBaseUrl,
                                  @Value("${fakestore.api.product}") String fakeStoreProductPath) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.productUrl = fakeStoreApiBaseUrl + productPath;
        this.productRequestUrl = fakeStoreApiBaseUrl + fakeStoreProductPath;
    }
    public FakeStoreProductDto getProductById(Long id) throws NotFoundException {
        //RestTemplate help to call Api(like fakestoreApi/selfProductApi)
        RestTemplate restTemplate = restTemplateBuilder.build();
        String Url=productUrl+id;
        System.out.println("Url:-"+Url);
        ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity(
                Url,
                FakeStoreProductDto.class);
        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        if (fakeStoreProductDto == null) {
            throw new NotFoundException("product with id: " + id + "not found");
        }
        //System.out.println(fakeStoreProductDto.getCategory());
        return fakeStoreProductDto;
    }

    public FakeStoreProductDto createProduct(GenericProductDto genericProductDto) {
        //for calling FakeStoreApi-->use RestTemplate
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.postForEntity(
                productRequestUrl,
                genericProductDto,
                FakeStoreProductDto.class);
        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        return fakeStoreProductDto;
    }
    public FakeStoreProductDto updateProductByid(Long Id,GenericProductDto genericProductDto) {
        //for calling FakeStoreApi-->use RestTemplate
        RestTemplate restTemplate = restTemplateBuilder.build();
        HttpEntity<GenericProductDto> requestBody=new HttpEntity<>(genericProductDto);
        String Url=productUrl+Id;
        ResponseEntity<FakeStoreProductDto> response = restTemplate.exchange(
                Url,
                HttpMethod.PUT,
                requestBody,
                FakeStoreProductDto.class);
        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        return fakeStoreProductDto;
    }

    public List<FakeStoreProductDto> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> response = restTemplate.getForEntity(productRequestUrl,
                FakeStoreProductDto[].class);
        FakeStoreProductDto[] fakeStoreProductDtos = response.getBody();
        return Arrays.asList(fakeStoreProductDtos);
    }
    public List<String> getAllCategories() {
        RestTemplate restTemplate = restTemplateBuilder.build();
         String Url=productUrl+"categories";
        System.out.println("Url:-"+Url);
        ResponseEntity<String[]> response = restTemplate.getForEntity(
                Url,
                String[].class);
        String[] categories = response.getBody();
        return Arrays.asList(categories);
    }

    public FakeStoreProductDto deleteProduct(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.exchange(productUrl,
                HttpMethod.DELETE,
                null,
                FakeStoreProductDto
                        .class, id);
        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        return fakeStoreProductDto;
    }

    public List<FakeStoreProductDto> getAllProductsInCategory(String name) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        String Url=productUrl+"category"+"/"+name;
        ResponseEntity<FakeStoreProductDto[]> response = restTemplate.getForEntity(
                Url,
                FakeStoreProductDto[].class);
        FakeStoreProductDto[] fakeStoreProductDtos = response.getBody();
        return Arrays.asList(fakeStoreProductDtos);
    }
}

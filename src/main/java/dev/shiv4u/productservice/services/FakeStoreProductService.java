package dev.shiv4u.productservice.services;

import dev.shiv4u.productservice.dtos.GenericProductDto;
import dev.shiv4u.productservice.exceptions.NotFoundException;
import dev.shiv4u.productservice.models.Category;
import dev.shiv4u.productservice.thirdpartyclients.fakestore.FakeStoreProductClient;
import dev.shiv4u.productservice.thirdpartyclients.fakestore.dtos.FakeStoreProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService {
    private final FakeStoreProductClient fakeStoreProductClient;

    @Autowired
    public FakeStoreProductService(FakeStoreProductClient fakeStoreProductClient) {
        this.fakeStoreProductClient = fakeStoreProductClient;
    }
    public GenericProductDto convertFakeStoreDtoToGenericProductDto(FakeStoreProductDto fakeStoreProductDto) {
        GenericProductDto genericProductDto = new GenericProductDto();
        genericProductDto.setId(fakeStoreProductDto.getId());
        genericProductDto.setImage(fakeStoreProductDto.getImage());
        genericProductDto.setTitle(fakeStoreProductDto.getTitle());
        genericProductDto.setPrice(fakeStoreProductDto.getPrice());
        genericProductDto.setDescription(fakeStoreProductDto.getDescription());
        genericProductDto.setCategory(fakeStoreProductDto.getCategory());
        return genericProductDto;
    }
    @Override
    public GenericProductDto getProductById(Long id) throws NotFoundException {
        return convertFakeStoreDtoToGenericProductDto(
                fakeStoreProductClient.getProductById(id));
    }
    @Override
    public List<Category> getAllCategories() {
        List<String> categories = fakeStoreProductClient.getAllCategories();
        List<Category> categoryList=new ArrayList<>();
        for(String category:categories){
            Category category1=new Category();
            category1.setName(category);
            categoryList.add(category1);
        }
        return categoryList;
    }
    @Override
    public List<GenericProductDto> getAllProductsInCategory(String name) {
        List<FakeStoreProductDto> fakeStoreProductDtos =
                fakeStoreProductClient.getAllProductsInCategory(name);
        List<GenericProductDto> genericProductDtos = new ArrayList<>();
        for (FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos) {
            GenericProductDto genericProductDto = convertFakeStoreDtoToGenericProductDto(fakeStoreProductDto);
            genericProductDtos.add(genericProductDto);
        }
        return genericProductDtos;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto product) {
        return convertFakeStoreDtoToGenericProductDto(
                fakeStoreProductClient.createProduct(product));
    }

    @Override
    public GenericProductDto updateProductByid(Long id,GenericProductDto genericProductDto) {
        return convertFakeStoreDtoToGenericProductDto(
                fakeStoreProductClient.updateProductByid(id,genericProductDto));
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        List<FakeStoreProductDto> fakeStoreProductDtos =
                fakeStoreProductClient.getAllProducts();
        List<GenericProductDto> genericProductDtos = new ArrayList<>();
        for (FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos) {
            GenericProductDto genericProductDto = convertFakeStoreDtoToGenericProductDto(fakeStoreProductDto);
            genericProductDtos.add(genericProductDto);
        }
        return genericProductDtos;
    }

   @Override
    public GenericProductDto deleteProduct(Long id) {
        return convertFakeStoreDtoToGenericProductDto(
                fakeStoreProductClient.deleteProduct(id)
        );
    }
}

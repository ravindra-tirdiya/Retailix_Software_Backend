package com.ecommers.billingsoftware.service.impl;

import com.ecommers.billingsoftware.entity.CategoryEntity;
import com.ecommers.billingsoftware.entity.ItemEntity;
import com.ecommers.billingsoftware.io.ItemRequest;
import com.ecommers.billingsoftware.io.ItemResponse;
import com.ecommers.billingsoftware.repository.CategoryRepository;
import com.ecommers.billingsoftware.repository.ItemRepository;
import com.ecommers.billingsoftware.service.FileUploadService;
import com.ecommers.billingsoftware.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final FileUploadService fileUploadService;
    private final CategoryRepository categoryRepository;

    @Override
    public ItemResponse addItem(ItemRequest request, MultipartFile file) {
        String imageUrl = fileUploadService.uploadFile(file);
        ItemEntity newItem = convertToEntity(request);
        CategoryEntity existingCategory = categoryRepository.findByCategoryId(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"+request.getCategoryId()));
        newItem.setCategory(existingCategory);
        newItem.setImgUrl(imageUrl);
        newItem = itemRepository.save(newItem);
        return convertToResponse(newItem);
    }

    private ItemResponse convertToResponse(ItemEntity newItem) {
        return ItemResponse.builder()
                .itemId(newItem.getItemId())
                .name(newItem.getName())
                .price(newItem.getPrice())
                .description(newItem.getDescription())
                .imgUrl(newItem.getImgUrl())
                .categoryName(newItem.getCategory().getName())
                .categoryId(newItem.getCategory().getCategoryId())
                .createdAt(newItem.getCreatedAt())
                .updatedAt(newItem.getUpdatedAt())
                .build();
    }

    private ItemEntity convertToEntity(ItemRequest request) {
        return ItemEntity.builder()
                .itemId(UUID.randomUUID().toString())
                .name(request.getName())
                .price(request.getPrice())
                .description(request.getDescription())
                .build();
    }

    @Override
    public List<ItemResponse> fatchItems() {
        return itemRepository.findAll()
                .stream()
                .map(itemEntity -> convertToResponse(itemEntity))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteItem(String itemId) {
       ItemEntity existingItem = itemRepository.findByItemId(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found"+itemId));
       boolean isFileDeleted = fileUploadService.deleteFile(existingItem.getImgUrl());
       if(isFileDeleted) {
           itemRepository.delete(existingItem);
       }else {
           throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR , "Unable to delete the image");
       }
    }
}

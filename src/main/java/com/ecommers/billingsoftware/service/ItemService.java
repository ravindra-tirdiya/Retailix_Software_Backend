package com.ecommers.billingsoftware.service;

import com.ecommers.billingsoftware.io.ItemRequest;
import com.ecommers.billingsoftware.io.ItemResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ItemService {
    ItemResponse addItem(ItemRequest request, MultipartFile file);
    List<ItemResponse> fatchItems();
    void deleteItem(String itemId);
}

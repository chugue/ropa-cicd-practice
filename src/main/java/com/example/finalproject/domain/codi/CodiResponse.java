package com.example.finalproject.domain.codi;

import com.example.finalproject.domain.admin.Admin;
import com.example.finalproject.domain.items.Items;
import com.example.finalproject.domain.codiItems.CodiItems;
import com.example.finalproject.domain.love.Love;
import com.example.finalproject.domain.photo.Photo;
import lombok.Data;

import java.util.List;
import java.util.Optional;

public class CodiResponse {

    //코디 등록에 아이템 등록 클릭시 나오는 뷰 DTO
    @Data
    public static class BrandInfo {
        private Integer brandId;
        private String brandName;
        private Integer photoId;
        private String photoName;
        private String photoPath;
        private List<ItemInfo> itemInfo;

        public BrandInfo(Admin admin, List<Items> items) {
            this.brandId = admin.getId();
            this.brandName = admin.getBrandName();
            this.photoId = admin.getPhoto().getId();
            this.photoName = admin.getPhoto().getName();
            this.photoPath = admin.getPhoto().getPath();
            this.itemInfo = items.stream().map(ItemInfo::new).toList();
        }

        @Data
        public static class ItemInfo {
            private Integer itemId;
            private String itemName;
            private String photoName;
            private String photoPath;
            private Boolean isMainPhoto;

            public ItemInfo(Items items) {
                this.itemId = items.getId();
                this.itemName = items.getName();
                this.photoName = items.getPhotos().getFirst().getName();
                this.photoPath = items.getPhotos().getFirst().getPath();
                this.isMainPhoto = items.getPhotos().getFirst().getIsMainPhoto();
            }
        }
    }


    //코디 등록 DTO
    @Data
    public static class SaveDTO {

    }

    @Data
    public static class NewLinkItems{
        private Integer codiId;
        private Integer userId;
        private String codiTitle;
        private List<SavedPhoto> savedPhotos;
        private List<LinkedItem> linkedItems;

        public NewLinkItems(Codi saveCodi, List<Photo> savedPhotos, List<CodiItems> linkedCodiItems) {
            this.codiId = saveCodi.getId();
            this.userId = saveCodi.getUser().getId();
            this.codiTitle = saveCodi.getTitle();
            this.savedPhotos = savedPhotos.stream().map(SavedPhoto::new).toList();
            this.linkedItems = linkedCodiItems.stream().map(LinkedItem::new).toList();
        }

        @Data
        public class SavedPhoto {
            private Integer photoId;
            private String photoName;
            private String photoPath;

            public SavedPhoto(Photo photo) {
                this.photoId = photo.getId();
                this.photoName = photo.getName();
                this.photoPath = photo.getPath();
            }
        }

        @Data
        public class LinkedItem {
            private Integer codiId;
            private Integer itemId;

            public LinkedItem(CodiItems codiItems) {
                this.codiId = codiItems.getCodi().getId();
                this.itemId = codiItems.getItems().getId();
            }
        }
    }

    @Data
    public static class MainViewDTO {
        private Integer codiId;
        private String description;
        private String createdAt;
        private Boolean isloved;
        private Long loveCount;
        private List<MainPhotoDTO> mainPhotos;
        private List<ItemsPhotoDTO> itemPhotos;
        private List<CodiPhotoDTO> otherCodiPhotos;

        public MainViewDTO(Codi codi, Optional<Love> codiLoveStatus, Long totalLoves, List<Photo> mainPhotos, List<Photo> itemPhotos, List<Photo> otherCodiPhotos) {
            this.codiId = codi.getId();
            this.description = codi.getDescription();
            this.createdAt = codi.getCreatedAt().toString();
            if (codiLoveStatus.isEmpty()) {
                this.isloved = false;
            } else {
                this.isloved = codiLoveStatus.get().getIsLoved();
            }
            this.loveCount = totalLoves;
            this.mainPhotos = mainPhotos.stream().map(photo ->
                    new MainPhotoDTO(photo)).toList();
            this.itemPhotos = itemPhotos.stream().map(photo ->
                    new ItemsPhotoDTO(photo)).toList();
            this.otherCodiPhotos = otherCodiPhotos.stream().map(photo ->
                    new CodiPhotoDTO(photo)).toList();
        }

        @Data
        public class MainPhotoDTO {
            private Integer mainPhotoId;
            private String mainPhotoName;
            private String mainPhotoPath;

            public MainPhotoDTO(Photo mainPhoto) {
                this.mainPhotoId = mainPhoto.getId();
                this.mainPhotoName = mainPhoto.getName();
                this.mainPhotoPath = mainPhoto.getPath();
            }
        }

        @Data
        public class ItemsPhotoDTO {
            private Integer itemsPhotoId;
            private String itemsPhotoName;
            private String itemsPhotoPath;

            public ItemsPhotoDTO(Photo photo) {
                this.itemsPhotoId = photo.getId();
                this.itemsPhotoName = photo.getName();
                this.itemsPhotoPath = photo.getPath();
            }
        }

        @Data
        public class CodiPhotoDTO {
            private Integer codiPhotoId;
            private String codiPhotoName;
            private String codiPhotoPath;

            public CodiPhotoDTO(Photo photo) {
                this.codiPhotoId = photo.getId();
                this.codiPhotoName = photo.getName();
                this.codiPhotoPath = photo.getPath();
            }
        }

    }

    @Data
    public static class OpenMainViewDTO {
        private Integer codiId;
        private String description;
        private String createdAt;
        private Boolean isloved;
        private Long loveCount;
        private List<MainPhotoDTO> mainPhotos;
        private List<ItemsPhotoDTO> itemPhotos;
        private List<CodiPhotoDTO> otherCodiPhotos;

        public OpenMainViewDTO(Codi codi, Long totalLoves, List<Photo> mainPhotos, List<Photo> itemPhotos, List<Photo> otherCodiPhotos) {
            this.codiId = codi.getId();
            this.description = codi.getDescription();
            this.createdAt = codi.getCreatedAt().toString();
            this.isloved = false;
            this.loveCount = totalLoves;
            this.mainPhotos = mainPhotos.stream().map(photo ->
                    new MainPhotoDTO(photo)).toList();
            this.itemPhotos = itemPhotos.stream().map(photo ->
                    new ItemsPhotoDTO(photo)).toList();
            this.otherCodiPhotos = otherCodiPhotos.stream().map(photo ->
                    new CodiPhotoDTO(photo)).toList();
        }

        @Data
        public class MainPhotoDTO {
            private Integer mainPhotoId;
            private String mainPhotoName;
            private String mainPhotoPath;

            public MainPhotoDTO(Photo mainPhoto) {
                this.mainPhotoId = mainPhoto.getId();
                this.mainPhotoName = mainPhoto.getName();
                this.mainPhotoPath = mainPhoto.getPath();
            }
        }

        @Data
        public class ItemsPhotoDTO {
            private Integer itemsPhotoId;
            private String itemsPhotoName;
            private String itemsPhotoPath;

            public ItemsPhotoDTO(Photo photo) {
                this.itemsPhotoId = photo.getId();
                this.itemsPhotoName = photo.getName();
                this.itemsPhotoPath = photo.getPath();
            }
        }

        @Data
        public class CodiPhotoDTO {
            private Integer codiPhotoId;
            private String codiPhotoName;
            private String codiPhotoPath;

            public CodiPhotoDTO(Photo photo) {
                this.codiPhotoId = photo.getId();
                this.codiPhotoName = photo.getName();
                this.codiPhotoPath = photo.getPath();
            }
        }

    }
}

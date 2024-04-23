package pojos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PetStorePetPojo {
    /**
     * {
     * "id": 0,
     * "category": {
     * * "id": 0,
     * *"name": "string"
     * },
     * "name": "doggie",
     * "photoUrls": [
     * *"string"
     * ],
     * "tags": [
     * * {
     * **"id": 0,
     * **"name": "string"
     * *}
     * ],
     * "status": "available"
     * }
     */

    private Long id;
    private PetCategoryPojo category;
    private String name;
    private  ArrayList<String> photoUrls;
    private  ArrayList<PetTagPojo>tags ;
    private String status;


    public PetStorePetPojo() {
    }

    public PetStorePetPojo(Long id, PetCategoryPojo category, String name, ArrayList<String> photoUrls, ArrayList <PetTagPojo> tags, String status) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.photoUrls = photoUrls;
        this.tags = tags;
        this.status = status;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PetCategoryPojo getCategory() {
        return category;
    }

    public void setCategory(PetCategoryPojo category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getPhotoUrls() {
        return photoUrls;
    }

    public void setPhotoUrls(ArrayList<String> photoUrls) {
        this.photoUrls = ( photoUrls);
    }

    public ArrayList<PetTagPojo> getTags() {
        return tags;
    }

    public void setTags(ArrayList<PetTagPojo> tags) {
        this.tags= tags;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", category=" + category +
                ", name='" + name + '\'' +
                ", photoUrls=" + photoUrls +
                ", tags=" + tags +
                ", status='" + status + '\'' +
                '}';
    }
}


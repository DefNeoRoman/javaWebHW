package resources;

public class ResourceServerController {
    private TestResource testResource;

    public ResourceServerController(TestResource testResource) {
        this.testResource = testResource;
    }
    public String getName() {
        return testResource.getName();
    }

    public int getAge() {
        return testResource.getAge();
    }
}

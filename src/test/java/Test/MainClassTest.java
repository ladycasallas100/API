package Test;

import Requests.GetRequests;
import Requests.PostRequests;
import org.testng.annotations.Test;

public class MainClassTest {
    @Test
    public void Token(){
        GetRequests getRequests = new GetRequests();
        getRequests.getToken();
    }

    @Test
    public void LoginSession(){
        PostRequests postRequests = new PostRequests();
        postRequests.getLoginWithLogin();
    }

    @Test
    public void CreateSession(){
        PostRequests postRequests = new PostRequests();
        postRequests.createSession();
    }

    @Test
    public void CreateList(){
    PostRequests postRequests = new PostRequests();
    postRequests.createList();

    }

    @Test
    public void addMovie() {
        PostRequests postRequests = new PostRequests();
        postRequests.addMovie();

    }
    @Test
    public void removeMovie() {
        PostRequests postRequests = new PostRequests();
        postRequests.removeMovie();

    }
}
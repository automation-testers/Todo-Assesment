package BaseClasses;


import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.open;

import static data.SystemProperties.ToDo_URL;
import static org.assertj.core.api.Assertions.assertThat;

public abstract class ToDoBaseTest extends BaseWebTest {
    private final List<Feature> features = new ArrayList<>();
    private String NCSAURL=null;

    protected ToDoBaseTest() {
    }

    public ToDoBaseTest add(Feature feature) {
        features.add(feature);
        return this;
    }

    public void trigger() {
        openAfdalAnalytics();

        new FeatureExecutor(features).execute();
    }

    protected void openAfdalAnalytics()
    {

//
//        if(AUTHENTICATION_REQUIRED.trim().equals("Yes") ){
//            NCSAURL  = "https://"+USERNAME+":"+PASSWORD+"@wwwncsa.ncsasports.org/";
//            System.err.println("Executing on URL-->" + NCSAURL);
//            open(NCSAURL);
//        }
//        else{
            assertThat(ToDo_URL).isNotNull();
            System.err.println("Executing on URL-->" + ToDo_URL);

            open(ToDo_URL);
        }

    }



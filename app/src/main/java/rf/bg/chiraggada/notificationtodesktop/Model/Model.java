package rf.bg.chiraggada.notificationtodesktop.Model;

import com.google.firebase.database.IgnoreExtraProperties;


@IgnoreExtraProperties
public class Model {


    public String pack;
    public String Title;
    public String Text;

    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public Model() {
    }

    public Model(String pack, String Title, String Text) {
        this.pack = pack;
        this.Title = Title;
        this.Text = Text;
    }

}

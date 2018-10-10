import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Word implements Serializable {
    protected String eng, vie;
    protected List<Word> data = new ArrayList<Word>();
    public Word(String Eng, String Vie){
        eng = Eng;
        vie = Vie;
    }
    public Word(){
        this("","");
    }
}

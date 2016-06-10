package alexkrongel.justplayaround;

/**
 * Created by alexandrakrongel on 6/9/16.
 */

public class SummaryChunk { //summary chunks only are for poetry and line #
    public int start;
    public int end;
    public String detail;

    public SummaryChunk(int start , String detail) {
        this.start = start;
        this.detail = detail;
    }
}

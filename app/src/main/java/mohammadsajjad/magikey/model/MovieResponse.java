package mohammadsajjad.magikey.model;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MovieResponse implements Parcelable
{

    @SerializedName("server_response")
    @Expose
    private List<Movies> movies = null;
    public final static Parcelable.Creator<MovieResponse> CREATOR = new Creator<MovieResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public MovieResponse createFromParcel(Parcel in) {
            return new MovieResponse(in);
        }

        public MovieResponse[] newArray(int size) {
            return (new MovieResponse[size]);
        }

    }
            ;

    protected MovieResponse(Parcel in) {
        in.readList(this.movies, (mohammadsajjad.magikey.model.Movies.class.getClassLoader()));
    }

    public MovieResponse() {
    }

    public List<Movies> getMovies() {
        return movies;
    }

    public void setMovies(List<Movies> movies) {
        this.movies = movies;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(movies);
    }

    public int describeContents() {
        return 0;
    }

}
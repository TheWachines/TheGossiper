package wchines.com.thegossiper.model.request;

/**
 * Created by IcaioLocal on 27/10/2016.
 */

public class QueryResponse<T> {

    T response;

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

}

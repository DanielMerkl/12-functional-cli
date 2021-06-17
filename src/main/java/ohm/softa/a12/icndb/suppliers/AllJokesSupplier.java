package ohm.softa.a12.icndb.suppliers;

import ohm.softa.a12.icndb.ICNDBApi;
import ohm.softa.a12.icndb.ICNDBService;
import ohm.softa.a12.model.JokeDto;
import ohm.softa.a12.model.ResponseWrapper;

import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

/**
 * Supplier implementation to retrieve all jokes of the ICNDB in a linear way
 *
 * @author Peter Kurfer
 */

public final class AllJokesSupplier implements Supplier<ResponseWrapper<JokeDto>> {

	/* ICNDB API proxy to retrieve jokes */
	private final ICNDBApi icndbApi;
	private int totalNumberOfJokes;
	private int lastJokeId = 0;

	public AllJokesSupplier() {
		icndbApi = ICNDBService.getInstance();

		try {
			totalNumberOfJokes = icndbApi.getJokeCount()
				.get()
				.getValue();
		} catch (InterruptedException | ExecutionException e) {
			totalNumberOfJokes = 0;
		}
	}

	public ResponseWrapper<JokeDto> get() {
		ResponseWrapper<JokeDto> joke = null;

		while (joke == null) {
			try {
				if (lastJokeId >= totalNumberOfJokes) {
					lastJokeId = 0;
				}
				joke = icndbApi.getJoke(lastJokeId).get();
				lastJokeId++;
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}

		return joke;
	}

}

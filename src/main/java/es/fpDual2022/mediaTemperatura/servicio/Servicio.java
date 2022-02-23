package es.fpDual2022.mediaTemperatura.servicio;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import es.fpDual2022.mediaTemperatura.modelo.Dato;

@Service
public class Servicio {

	// Método llamada aemet

	public Dato llamadaAemet(Long codigo) {

		HttpClient client = HttpClients.createDefault();
		HttpGet peticion = new HttpGet("http://localhost:8002/temperatura/datos/" + codigo);

		try {
			HttpResponse httpResponse = client.execute(peticion);
			String resultado = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
			JSONObject respuestaJson = new JSONObject(resultado);

			Dato salida = new Dato();
			salida.setTemperaturaMaxima(respuestaJson.getInt("temperaturaMaxima"));
			salida.setTemperaturaMinima(respuestaJson.getInt("temperaturaMinima"));

			return salida;

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	// Metodo llamada suma

	public Integer suma(int numero1, int numero2) {
		HttpClient client = HttpClients.createDefault();
		HttpGet peticion = new HttpGet("http://localhost:8003/calculator/add/" + numero1 + "/" + numero2);

		try {
			HttpResponse httpResponse = client.execute(peticion);
			Integer resultado = Integer.parseInt(EntityUtils.toString(httpResponse.getEntity(), "UTF-8"));
			return resultado;

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	// Metodo llamada division

	public Integer division(int numero1, int numero2) {
		HttpClient client = HttpClients.createDefault();
		HttpGet peticion = new HttpGet("http://localhost:8003/calculator/divide/" + numero1 + "/" + numero2);

		try {
			HttpResponse httpResponse = client.execute(peticion);
			Integer resultado = Integer.parseInt(EntityUtils.toString(httpResponse.getEntity(), "UTF-8"));
			return resultado;

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}

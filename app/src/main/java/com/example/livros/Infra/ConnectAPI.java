package com.example.livros.Infra;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

public class ConnectAPI {
    private final String TAG = "Http";
    public final int TIMEOUT_MILLIS = 15000;
    public boolean LOG_ON = false;
    private String contentType;
    private String charsetToEncode;

    public String doGet(String url) throws IOException {
        return doGet(url, null, "UTF-8");
    }

    public String doGet(String url, Map<String, String> params, String charset) throws IOException {
        String queryString = getQueryString(params);
        if (queryString != null && queryString.trim().length() > 0) {
            url += "?" + queryString;
        }

        if (LOG_ON) {
            Log.d(TAG, ">> Http.doGet: " + url);
        }

        URL u = new URL(url);
        HttpURLConnection conn = null;
        String s = null;
        try {
            conn = (HttpURLConnection) u.openConnection();
            if (contentType != null) {
                conn.setRequestProperty("Content-Type", contentType);
            }
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(TIMEOUT_MILLIS);
            conn.setReadTimeout(TIMEOUT_MILLIS);
            conn.connect();
            InputStream in = null;
            int status = conn.getResponseCode();
            if (status >= HttpURLConnection.HTTP_BAD_REQUEST) {
                Log.d(TAG, "Error code: " + status);
                in = conn.getErrorStream();
            } else {
                in = conn.getInputStream();
            }
            s = IOUtils.toString(in, charset);
            if (LOG_ON) {
                Log.d(TAG, "<< Http.doGet: " + s);
            }
            in.close();
        } catch (IOException e) {
            throw e;
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

        return s;
    }


    public static String getJadiel(String completeUrl, String body) {
        String jsonResposta = null;

        try {
            URL url = new URL(completeUrl);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

            conexao.setRequestMethod("GET");
            conexao.addRequestProperty("Content-type", "application/json;charset=utf-8");
            //conexao.setRequestProperty("authorization", "\"token\"" + ":\"" + SessaoApplication.instance.getTokenUser() + "\"");

            conexao.setDoOutput(true);
            conexao.setDoInput(true);

            PrintStream printStream = new PrintStream(conexao.getOutputStream());
            printStream.println(body);

            conexao.connect();

            BufferedReader reader = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            StringBuilder sbHtml = new StringBuilder();
            String linha;

            while ((linha = reader.readLine()) != null) {
                sbHtml.append(linha);
            }
            jsonResposta = sbHtml.toString();
            reader.close();
            printStream.close();
            conexao.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }

        //usuarioService.setRespostaServidor(jsonResposta);
        SessaoApplication.getInstance().setResposta(jsonResposta);
        Log.d("respostajson",jsonResposta);
        Log.i("respostajson",jsonResposta);
        return jsonResposta;
    }

    /**
     * Retorna a QueryString para 'GET'
     */
    public String getQueryString(Map<String, String> params) throws IOException {
        if (params == null || params.size() == 0) {
            return null;
        }
        String urlParams = null;
        for (String chave : params.keySet()) {
            Object objValor = params.get(chave);
            if (objValor != null) {
                String valor = objValor.toString();
                if (charsetToEncode != null) {
                    valor = URLEncoder.encode(valor, charsetToEncode);
                }
                urlParams = urlParams == null ? "" : urlParams + "&";
                urlParams += chave + "=" + valor;
            }
        }
        return urlParams;

    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setCharsetToEncode(String encode) {
        this.charsetToEncode = encode;
    }

}

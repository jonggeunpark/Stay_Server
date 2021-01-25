package com.stay.stay.service;

import com.stay.stay.constants.Covid19;
import com.stay.stay.domain.Corona;
import com.stay.stay.domain.User;
import com.stay.stay.exception.NotFoundException;
import com.stay.stay.repository.CoronaRepository;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CoronaService {

    private final CoronaRepository coronaRepository;

    public Corona findById(Long coronaId) {
        return coronaRepository.findById(coronaId).orElseThrow(() -> new NotFoundException("Corona Not Found"));
    }

    @Transactional
    public void saveCorona(Corona corona) {coronaRepository.save(corona);}

    private static String getTagValue(String tag, Element eElement) {
        NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
        Node nValue = (Node) nlList.item(0);
        if(nValue == null)
            return null;
        return nValue.getNodeValue();
    }

    public static Date addDay(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, days);
        return cal.getTime();
    }

    @Transactional
    @Scheduled(cron = "0 0 10 * * *")
    public void updateCorona() {

        Corona corona = findById(1L);
        String ServiceKey = Covid19.ServiceKey;
        Date today = new Date();
        Date yesterday = addDay(today, -1);

        SimpleDateFormat date = new SimpleDateFormat("yyyyMMdd");
        String startCreateDt = date.format(yesterday);
        String endCreateDt = date.format(today);

        try{

            StringBuilder urlBuilder = new StringBuilder("http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19InfStateJson");
            urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=" + ServiceKey);
            urlBuilder.append("&" + URLEncoder.encode("startCreateDt","UTF-8") + "=" + URLEncoder.encode(startCreateDt, "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("endCreateDt","UTF-8") + "=" + URLEncoder.encode(endCreateDt, "UTF-8"));
            URL url = new URL(urlBuilder.toString());

            DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
            Document doc = dBuilder.parse(String.valueOf(url));

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("item");

            int decideYesterday = -1;
            int decideToday = -1;
            int cnt;
            String stateDt = startCreateDt;

            for(int temp = 0; temp < nList.getLength(); temp++){
                Node nNode = nList.item(temp);
                if(nNode.getNodeType() == Node.ELEMENT_NODE){

                    Element eElement = (Element) nNode;
                    if(Objects.equals(getTagValue("stateDt", eElement), startCreateDt)) {
                        decideYesterday = Integer.parseInt(Objects.requireNonNull(getTagValue("decideCnt", eElement)));
                    }

                    if(Objects.equals(getTagValue("stateDt", eElement), endCreateDt)) {
                        decideToday = Integer.parseInt(Objects.requireNonNull(getTagValue("decideCnt", eElement)));
                        stateDt = getTagValue("STATE_DT", eElement);
                    }

                }
            }

            cnt = decideToday - decideYesterday;
            corona.setSTATE_DT(stateDt);
            corona.setDECIDE_CNT(cnt);
            saveCorona(corona);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

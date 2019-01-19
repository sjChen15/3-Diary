package io.indico.api.results;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.indico.api.Api;
import io.indico.api.ApiType;
import io.indico.api.image.FacialEmotion;
import io.indico.api.text.Category;
import io.indico.api.text.Emotion;
import io.indico.api.text.Language;
import io.indico.api.text.Persona;
import io.indico.api.text.Personality;
import io.indico.api.text.PoliticalClass;
import io.indico.api.text.TextTag;
import io.indico.api.utils.EnumParser;
import io.indico.api.utils.ImageUtils;
import io.indico.api.utils.IndicoException;

/**
 * Created by Chris on 6/22/15.
 */
public class IndicoResult {
    Map<Api, Object> results;

    @SuppressWarnings("unchecked")
    public IndicoResult(Api api, Map<String, ?> response) throws IndicoException {
        this.results = new HashMap<>();
        if (!response.containsKey("results")) {
            throw new IndicoException(api + " failed with error " +
                (response.containsKey("error") ? response.get("error") : "unexpected error")
            );
        }

        if (api.type != ApiType.Multi)
            results.put(api, response.get("results"));
        else {
            Map<String, ?> responses = (Map<String, ?>) response.get("results");
            for (Api res : Api.values()) {
                Map<String, ?> apiResponse = (Map<String, ?>) responses.get(res.toString());
                if (apiResponse == null)
                    continue;
                if (apiResponse.containsKey("error"))
                    throw new IndicoException(api + " failed with error " + apiResponse.get("error"));
                results.put(res, apiResponse.get("results"));
            }
        }
    }

    public Double getSentiment() throws IndicoException {
        return (Double) get(Api.Sentiment);
    }

    public Double getSentimentHQ() throws IndicoException {
        return (Double) get(Api.SentimentHQ);
    }

    @SuppressWarnings("unchecked")
    public Map<PoliticalClass, Double> getPolitical() throws IndicoException {
        return EnumParser.parse(PoliticalClass.class, (Map<String, Double>) get(Api.Political));
    }

    @SuppressWarnings("unchecked")
    public Map<Language, Double> getLanguage() throws IndicoException {
        return EnumParser.parse(Language.class, (Map<String, Double>) get(Api.Language));
    }

    @SuppressWarnings("unchecked")
    public Map<Emotion, Double> getEmotion() throws IndicoException {
        return EnumParser.parse(Emotion.class, (Map<String, Double>) get(Api.Emotion));
    }

    @SuppressWarnings("unchecked")
    public Map<TextTag, Double> getTextTags() throws IndicoException {
        return EnumParser.parse(TextTag.class, (Map<String, Double>) get(Api.TextTags));
    }

    @SuppressWarnings("unchecked")
    public List<Double> getTextFeatures() throws IndicoException {
        return (List<Double>) get(Api.TextFeatures);
    }

    @SuppressWarnings("unchecked")
    public Map<FacialEmotion, Double> getFer() throws IndicoException {
        return EnumParser.parse(FacialEmotion.class, (Map<String, Double>) get(Api.FER));
    }

    @SuppressWarnings("unchecked")
    public Map<Rectangle, Map<FacialEmotion, Double>> getLocalizedFer() throws IndicoException {
        Map<Rectangle, Map<FacialEmotion, Double>> ret = new HashMap<>();

        try {
            List<Map<String, Object>> result = (List<Map<String, Object>>) get(Api.FER);
            for (Map<String, Object> res : result) {
                ret.put(ImageUtils.getRectangle((Map<String, List<Double>>) res.get("location")),
                    EnumParser.parse(FacialEmotion.class, (Map<String, Double>) res.get("emotions"))
                );
            }
        } catch (ClassCastException e) {
            e.printStackTrace();
        }

        return ret;
    }

    @SuppressWarnings("unchecked")
    public List<Double> getImageFeatures() throws IndicoException {
        return (List<Double>) get(Api.ImageFeatures);
    }

    @SuppressWarnings("unchecked")
    public List<Double> getFacialFeatures() throws IndicoException {
        return (List<Double>) get(Api.FacialFeatures);
    }

    @SuppressWarnings("unchecked")
    public Map<String, Double> getKeywords() throws IndicoException {
        return (Map<String, Double>) get(Api.Keywords);
    }

    @SuppressWarnings("unchecked")
    public Map<String, Double> getImageRecognition() throws IndicoException {
        return (Map<String, Double>) get(Api.ImageRecognition);
    }

    @SuppressWarnings("unchecked")
    public Double getContentFiltering() throws IndicoException {
        return (Double) get(Api.ContentFiltering);
    }

    @SuppressWarnings("unchecked")
    public Double getTwitterEngagement() throws IndicoException {
        return (Double) get(Api.TwitterEngagement);
    }

    @SuppressWarnings("unchecked")
    public List<Rectangle> getFacialLocalization() throws IndicoException {
        List<Rectangle> rectangles = new ArrayList<>();
        for (Map<String, List<Double>> each : (List<Map<String, List<Double>>>) get(Api.FacialLocalization)) {
            rectangles.add(ImageUtils.getRectangle(each));
        }

        return rectangles;
    }

    @SuppressWarnings("unchecked")
    public Map<Personality, Double> getPersonality() throws IndicoException {
        return EnumParser.parse(Personality.class, (Map<String, Double>) get(Api.Personality));
    }

    @SuppressWarnings("unchecked")
    public Map<Persona, Double> getPersona() throws IndicoException {
        return EnumParser.parse(Persona.class, (Map<String, Double>) get(Api.Persona));
    }

    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> getPeople() throws IndicoException {
        return (List<Map<String, Object>>) get(Api.People);
    }

    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> getPlaces() throws IndicoException {
        return (List<Map<String, Object>>) get(Api.Places);
    }

    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> getOrganizations() throws IndicoException {
        return (List<Map<String, Object>>) get(Api.Organizations);
    }

    @SuppressWarnings("unchecked")
    public List<Double> getRelevance() throws IndicoException {
        return (List<Double>) get(Api.Relevance);
    }


    @SuppressWarnings("unchecked")
    public List<Map<String, Map<String, Map<String, Double>>>> getIntersections() throws IndicoException {
        throw new IndicoException("Intersections Api should be called with more than 1 example as a Batch Request");
    }

    private Object get(Api api) throws IndicoException {
        if (!results.containsKey(api))
            throw new IndicoException(api.toString() + " was not included in the request");
        return results.get(api);
    }
}

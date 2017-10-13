package com.web.model;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.web.model.EventModel;

/**
 * Created by admin on 2017/10/12.
 */

public class MayProcessItemsModel {


    private boolean permission;
    private String procStatus;
    private String stateCaption;
    private String[] editFields;
    private List<EventModel> eventList;

    public boolean isPermission() {
        return permission;
    }

    public void setPermission(boolean permission) {
        this.permission = permission;
    }

    public String getProcStatus() {
        return procStatus;
    }

    public void setProcStatus(String procStatus) {
        this.procStatus = procStatus;
    }

    public String getStateCaption() {
        return stateCaption;
    }

    public void setStateCaption(String stateCaption) {
        this.stateCaption = stateCaption;
    }

    public String[] getEditFields() {
        return editFields;
    }

    public void setEditFields(String[] editFields) {
        this.editFields = editFields;
    }

    public List<EventModel> getEventList() {
        return eventList;
    }

    public void setEventList(List<EventModel> eventList) {
        this.eventList = eventList;
    }

    public MayProcessItemsModel(JSONObject object) {
        this.permission = object.getBoolean("permission");

        this.editFields = object.getString("editfields").replace("[", "").replace("]", "").split(",");

        this.procStatus = object.getString("procstatus");

        this.stateCaption = object.getString("statecaption");

        List<EventModel> list = new ArrayList();


        JSONArray array = object.getJSONArray("eventlist");

        if (array != null) {
            for (int i = 0; i < array.size(); i++) {
                String eventType = array.getJSONObject(i).getString("event");
                String name = array.getJSONObject(i).getString("name");

                EventModel model = new EventModel();
                model.setEvent(eventType);
                model.setName(name);
                list.add(model);
            }
        }
        this.setEventList(list);
    }

}

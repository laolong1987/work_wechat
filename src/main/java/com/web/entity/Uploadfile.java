package com.web.entity;

/**
 * Created by gaoyang on 2016/10/30.
 */

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "uploadfile")
public class Uploadfile {
    @Id
    @GeneratedValue( generator = "uuid" )
    @GenericGenerator(
            name = "uuid",
            strategy = "org.hibernate.id.UUIDGenerator",
            parameters = {
                    @Parameter(
                            name = "uuid_gen_strategy_class",
                            value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
                    )
            }
    )
    private String id;
    @Column(name ="filename")
    private String filename;
    @Column(name ="mimetype")
    private String mimetype;
    @Column(name ="uuid")
    private String uuid;
    @Column(name ="filepath")
    private String filepath;
    @Column(name ="source_entity")
    private String source_entity;
    @Column(name ="source_id")
    private String source_id;
    @Column(name ="type")
    private int type;
    @Column(name ="file_type")
    private int file_type;
    @Column(name ="file_size")
    private long file_size;
    @Column(name ="create_time")
    private Date create_time;
    @Column(name ="update_time")
    private Date update_time;
    public void setId(String id){
        this.id=id;
    }
    public String getId(){
        return this.id;
    }

    public long getFile_size() {
        return file_size;
    }

    public void setFile_size(long file_size) {
        this.file_size = file_size;
    }

    public int getFile_type() {
        return file_type;
    }

    public void setFile_type(int file_type) {
        this.file_type = file_type;
    }

    public void setFilename(String filename){
        this.filename=filename;
    }
    public String getFilename(){
        return this.filename;
    }

    public void setMimetype(String mimetype){
        this.mimetype=mimetype;
    }
    public String getMimetype(){
        return this.mimetype;
    }

    public void setUuid(String uuid){
        this.uuid=uuid;
    }
    public String getUuid(){
        return this.uuid;
    }

    public void setFilepath(String filepath){
        this.filepath=filepath;
    }
    public String getFilepath(){
        return this.filepath;
    }


    public void setType(int type){
        this.type=type;
    }
    public int getType(){
        return this.type;
    }

    public void setCreate_time(Date create_time){
        this.create_time=create_time;
    }
    public Date getCreate_time(){
        return this.create_time;
    }

    public void setUpdate_time(Date update_time){
        this.update_time=update_time;
    }
    public Date getUpdate_time(){
        return this.update_time;
    }

    public String getSource_entity() {
        return source_entity;
    }

    public void setSource_entity(String source_entity) {
        this.source_entity = source_entity;
    }

    public String getSource_id() {
        return source_id;
    }

    public void setSource_id(String source_id) {
        this.source_id = source_id;
    }
}
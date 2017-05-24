package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

@Entity
public class Commodity extends Model {
    @Id
    public int commodityId;
    
    public String commodityName;
    public double price;
    public double agio;
    public String picture;
    public String cType;
    public String user;
    
    public Commodity(String commodityName,double price,double agio,String picture,String cType,String user){
        this.commodityName = commodityName;
        this.price = price;
        this.agio = agio;
        this.picture = picture;
        this.cType = cType;
        this.user = user;
    }
    
    public static Finder<Integer,Commodity> find =  new Finder<Integer,Commodity>(Integer.class, Commodity.class);
    
    public static List<Commodity> findAll() {
        return find.all();
    }
    
    public static Commodity findById (int commodityId) {              //根据id返回商品
        return find.where().eq("commodityId", commodityId).findUnique();
    }
    
    public static Commodity findByUser (String username) {                //返回属于某个商家的所有商品
        return find.where().eq("user", username).findUnique();
    }
    
    public static void delById (int commodityId) {                //返回属于某个商家的所有商品
        Commodity commodity = findById(commodityId);
        commodity.delete();
    }
    
    public static boolean isbelong (String username,int commodityId) {    //某个商品是否属于某个商家
        Commodity commodity = findById(commodityId);
        return commodity!=null && commodity.user.equals(username);
    }

}
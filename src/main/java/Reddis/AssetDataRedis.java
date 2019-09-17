package Reddis;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

import java.util.Map;

public class AssetDataRedis {

    public void insertIntoRedis(String assetObject) {

        RedisClient client = RedisClient.create("redis://localhost");
        StatefulRedisConnection<String, String> connection = client.connect();
        RedisCommands<String, String> syncCommands = connection.sync();

        String assetArray[] = new String[4];
        assetObject = assetObject.substring(1, assetObject.length());

        for(int i = 0; i < 4; i++) {
            if(i != 3) {
                assetArray[i] = assetObject.substring(0, assetObject.indexOf(','));
                assetObject = assetObject.substring(assetObject.indexOf(',') + 1);
            }
            else {
                assetArray[i] = assetObject.substring(0, assetObject.length()-1);
            }
        }
        for(int x = 0; x < assetArray.length; x++) {
            System.out.println(assetArray[x]);
        }

        syncCommands.hset("asset_class:001", "Date", assetArray[0]);
        syncCommands.hset("asset_class:001", "Location", assetArray[1]);
        syncCommands.hset("asset_class:001", "Description", assetArray[2]);
        syncCommands.hset("asset_class:001", "Reported By", assetArray[3]);

        System.out.println("Added to Redis");

        Map<String, String> record = syncCommands.hgetall("asset_class:001");

        System.out.println("Printing key: asset_class:001");
        System.out.println("Date : " + record.get("Date"));
        System.out.println("Location: " + record.get("Location"));
        System.out.println("Description: " + record.get("Description"));
        System.out.println("Reported by: " + record.get("Reported By"));

        connection.close();
    }
}

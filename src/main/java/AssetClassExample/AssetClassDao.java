package AssetClassExample;

import org.springframework.stereotype.Repository;

@Repository
public class AssetClassDao {
    private static AssetClasses list = new AssetClasses();

    static {
        list.getAssetClassList().add(new AssetClass(1, "1/1/1900", "Brooklyn", "Faulty repair", "Tanner S"));
    }

    public AssetClasses getAllClasses() {
        return list;
    }

    public void addClasss(AssetClass assetClass) {
        list.getAssetClassList().add(assetClass);
    }
}

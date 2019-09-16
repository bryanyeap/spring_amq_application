package AssetClassExample;

import java.util.ArrayList;
import java.util.List;

public class AssetClasses {

    private List<AssetClass> assetClassList;

    public List<AssetClass> getAssetClassList() {
        if(assetClassList == null) {
            assetClassList = new ArrayList<>();
        }
        return assetClassList;
    }

    public void setAssetClassList(List<AssetClass> assetClassList) {
        this.assetClassList = assetClassList;
    }
}

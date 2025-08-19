package org.example.phaze2.controllers.sceneControllers.gameSceneController;

import org.example.phaze2.controllers.sceneControllers.gameSceneController.interfaces.ShopPort;
import org.example.phaze2.controllers.shopController.ShopController;

public final class ShopAdapter implements ShopPort {
    private final ShopController shop;
    public ShopAdapter(ShopController shop) { this.shop = shop; }
    @Override public void open() { shop.OpenShop(); }
    @Override public void close() { shop.CloseShop(); }
}

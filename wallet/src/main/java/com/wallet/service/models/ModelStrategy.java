package com.wallet.service.models;

public enum ModelStrategy {
    WALLET(new Wallet(), "wallet"),
    USER(new User(), "user");

    private final Model model;
    private final String type;

    ModelStrategy(Model model, String type) {
        this.model = model;
        this.type = type;
    }

    public static Model findModel(String type) {
        for (ModelStrategy modelStrategy : ModelStrategy.values()) {
            if (type.equals(modelStrategy.getType())) {
                return modelStrategy.getModel();
            }
        }
        return null;
    }

    public String getType() {
        return type;
    }

    public Model getModel() {
        return model;
    }
}

package app.components.entity;

import lombok.Getter;

@Getter
public enum OperationType {

    CREATE ("create"),
    UPDATE ("update"),
    DELETE ("delete");

    private String title;

    OperationType(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }

}

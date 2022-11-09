package sql.demo.model;
import java.math.BigDecimal;

// Модель акции

public class Share extends BaseModel {
    // поля SQL таблицы и соответствующие им поля модели
    // типы данных SQL
    private String name; //Наименование
    private BigDecimal startPrice; //Начальная цена
    private int changeProbability; // Вероятность смены курса (в процентах)
    private int delta;   // Максимальное колебание (в процентах)стоимости акции в результате торгов

    public Share() {
    }

    public Share (long id, String name, BigDecimal startPrice, int changeProbability, int delta) {
        super(id);
        this.name = name;
        this.startPrice = startPrice;
        this.changeProbability = changeProbability;
        this.delta = delta;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(BigDecimal startPrice) {
        this.startPrice = startPrice;
    }

    public int getChangeProbability() {
        return changeProbability;
    }

    public void setChangeProbability(int changeProbability) {
        this.changeProbability = changeProbability;
    }

    public int getDelta() {
        return delta;
    }

    public void setDelta(int delta) {
        this.delta = delta;
    }
}

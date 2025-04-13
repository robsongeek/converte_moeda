package moeda;
import java.util.Map;

public record MoedaRecord(String base_code, Map<String, Double> conversion_rates) {
}

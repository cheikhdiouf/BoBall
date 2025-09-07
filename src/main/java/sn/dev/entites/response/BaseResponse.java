package sn.dev.entites.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.ComponentScan;

import java.io.Serializable;
import java.util.Map;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ComponentScan(basePackages = "sn.atos")
public class BaseResponse<T> implements Serializable {
    private BaseResponseStatut statut;
    private BaseResponseContent<T> content;
    private  Integer codeRetour;
    private  String messageRetour;
    private Map<String, Object> totaux;
    private Long  totalItems;
    private Map<String,Object> extra;

    public BaseResponse<T> statut(BaseResponseStatut statut) {
        this.statut = statut;
        return this;
    }

   public BaseResponse<T>content(String key ,T data) {
        this.content = new BaseResponseContent<>(key, data);
        return this;

   }
   public BaseResponse<T>codeRetour(Integer codeRetour) {
        this.codeRetour = codeRetour;
        return this;
   }
   public BaseResponse<T>messageRetour(String messageRetour) {
        this.messageRetour = messageRetour;
        return this;
   }
   public BaseResponse<T>totalItems(Long totalItems) {
        this.totalItems = totalItems;
        return this;
   }
   private BaseResponse<T>extra(Map<String,Object> extra) {
        this.extra = extra;
        return this;
   }
   public BaseResponse<T>totaux(Map<String,Object> totaux) {
        this.totaux = totaux;
        return this;
   }
}

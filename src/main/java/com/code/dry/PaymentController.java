package com.code.dry;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

  private final PaymentService paymentService = new PaymentService();

  @GetMapping
  public ResponseEntity<?> list(
      @RequestHeader(value = "Authorization", required = false) String auth) {
    return withAuth(auth, () -> {
      var user = paymentService.userFromToken(extractToken(auth));
      var payments = paymentService.list(user);
      return ResponseEntity.ok(Map.of("data", payments, "count", payments.size()));
    });
  }

  @PostMapping("/{id}/capture")
  public ResponseEntity<?> capture(
      @RequestHeader(value = "Authorization", required = false) String auth,
      @PathVariable String id) {
    return withAuthAndId(auth, id, parsedId -> {
      var user = paymentService.userFromToken(extractToken(auth));
      var result = paymentService.capture(user, parsedId);
      return ResponseEntity.ok(Map.of("success", result));
    });
  }

  @PostMapping("/{id}/refund")
  public ResponseEntity<?> refund(
      @RequestHeader(value = "Authorization", required = false) String auth,
      @PathVariable String id,
      @RequestBody Map<String, Object> body) {
    return withAuthAndId(auth, id, parsedId -> {
      if (!body.containsKey("reason") || String.valueOf(body.get("reason")).isBlank()) {
        return ResponseEntity.badRequest().body(Map.of("error", "reason required"));
      }
      var user = paymentService.userFromToken(extractToken(auth));
      var result = paymentService.refund(user, parsedId, String.valueOf(body.get("reason")));
      return ResponseEntity.ok(Map.of("success", result));
    });
  }

  private ResponseEntity<?> withAuth(String auth, Supplier<ResponseEntity<?>> action) {
    if (!isBearer(auth)) {
      return ResponseEntity.status(401).body(Map.of("error", "Unauthorized"));
    }
    try {
      return action.get();
    } catch (Exception e) {
      return ResponseEntity.internalServerError().body(Map.of("error", e.getMessage()));
    }
  }

  private ResponseEntity<?> withAuthAndId(
      String auth, String id, Function<Long, ResponseEntity<?>> action) {
    return withAuth(auth, () -> {
      if (!id.matches("\\d+")) {
        return ResponseEntity.badRequest().body(Map.of("error", "invalid id"));
      }
      return action.apply(Long.parseLong(id));
    });
  }

  private static boolean isBearer(String auth) {
    return auth != null && auth.startsWith("Bearer ");
  }

  private static String extractToken(String auth) {
    return auth.substring(7);
  }
}

class PaymentService {

  Object userFromToken(String token) {
    return Map.of("id", 1);
  }

  java.util.List<Map<String, Object>> list(Object user) {
    return java.util.List.of(Map.of("id", 101, "status", "ok"));
  }

  boolean capture(Object user, long id) {
    return true;
  }

  boolean refund(Object user, long id, String reason) {
    return true;
  }
}

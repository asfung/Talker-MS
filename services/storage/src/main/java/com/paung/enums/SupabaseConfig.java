package com.paung.enums;

import org.springframework.beans.factory.annotation.Value;

public enum SupabaseConfig {
  @Value("${application.config.supabase-storage_bucket-url}")
  SUPABASE_STORAGE_BUCKET_URL,
  @Value("${application.config.supabase-storage-url}")
  SUPABASE_STORAGE_URL,
  @Value("${application.config.supabase-auth-token}")
  SUPABASE_AUTH_TOKEN;
}

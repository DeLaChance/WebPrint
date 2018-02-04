/*
 * Copyright 2014 Red Hat, Inc.
 *
 * Red Hat licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package nl.webprint.reactivex.configuration;

import java.util.Map;
import io.reactivex.Observable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.vertx.reactivex.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

/**
 * Maintains the runtime configuration of this app.
 *
 * <p/>
 * NOTE: This class has been automatically generated from the {@link nl.webprint.configuration.ConfigurationRepository original} non RX-ified interface using Vert.x codegen.
 */

@io.vertx.lang.reactivex.RxGen(nl.webprint.configuration.ConfigurationRepository.class)
public class ConfigurationRepository {

  @Override
  public String toString() {
    return delegate.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ConfigurationRepository that = (ConfigurationRepository) o;
    return delegate.equals(that.delegate);
  }
  
  @Override
  public int hashCode() {
    return delegate.hashCode();
  }

  public static final io.vertx.lang.reactivex.TypeArg<ConfigurationRepository> __TYPE_ARG = new io.vertx.lang.reactivex.TypeArg<>(
    obj -> new ConfigurationRepository((nl.webprint.configuration.ConfigurationRepository) obj),
    ConfigurationRepository::getDelegate
  );

  private final nl.webprint.configuration.ConfigurationRepository delegate;
  
  public ConfigurationRepository(nl.webprint.configuration.ConfigurationRepository delegate) {
    this.delegate = delegate;
  }

  public nl.webprint.configuration.ConfigurationRepository getDelegate() {
    return delegate;
  }

  public static ConfigurationRepository create(Vertx vertx) { 
    ConfigurationRepository ret = ConfigurationRepository.newInstance(nl.webprint.configuration.ConfigurationRepository.create(vertx.getDelegate()));
    return ret;
  }

  public static ConfigurationRepository createProxy(Vertx vertx) { 
    ConfigurationRepository ret = ConfigurationRepository.newInstance(nl.webprint.configuration.ConfigurationRepository.createProxy(vertx.getDelegate()));
    return ret;
  }

  /**
   * Reads an item from the configuration store and parses it to a JSON object.
   * @param key 
   * @param resultHandler 
   */
  public void read(String key, Handler<AsyncResult<JsonObject>> resultHandler) { 
    delegate.read(key, resultHandler);
  }

  /**
   * Reads an item from the configuration store and parses it to a JSON object.
   * @param key 
   * @return 
   */
  public Single<JsonObject> rxRead(String key) { 
    return new io.vertx.reactivex.core.impl.AsyncResultSingle<JsonObject>(handler -> {
      read(key, handler);
    });
  }


  public static  ConfigurationRepository newInstance(nl.webprint.configuration.ConfigurationRepository arg) {
    return arg != null ? new ConfigurationRepository(arg) : null;
  }
}

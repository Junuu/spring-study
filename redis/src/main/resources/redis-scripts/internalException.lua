redis.call('SET', KEYS[1], 'stringValue')
redis.call('DECR', KEYS[1])
redis.call('SET', KEYS[1], 'changeValue')

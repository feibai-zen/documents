--���Ƶ�Key
local key = KEYS[1]
--�����Ĵ�С
local limit = tonumber(ARGV[1])

--�Ѿ��е�����
local current = tonumber(redis.call('get', key) or "0")
if current + 1 > limit then
  --�ܾ��ͻ��˵Ĳ���
    return 0
else
    redis.call("INCRBY", key,"1")
    redis.call("expire", key,"2")
end
return 1
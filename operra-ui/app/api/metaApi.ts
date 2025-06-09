// app/api/metaApi.ts

import { CrudContextDto, LookupResultDto } from '@/types/crud';

const BASE_URL = process.env.NEXT_PUBLIC_API_BASE_URL;

export async function fetchCrudContext(entity: string): Promise<CrudContextDto> {
    const response = await fetch(`${BASE_URL}/meta/context/${entity}`);
    if (!response.ok) {
        throw new Error(`خطا در دریافت متا برای ${entity}`);
    }
    return await response.json();
}

export async function fetchLookupOptions(lookupId: string): Promise<LookupResultDto[]> {
    const response = await fetch(`${BASE_URL}/meta/lookup/${lookupId}`);
    if (!response.ok) {
        throw new Error(`خطا در دریافت lookup برای ${lookupId}`);
    }
    return await response.json();
}
